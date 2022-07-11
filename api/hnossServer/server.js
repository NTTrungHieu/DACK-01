const fs = require('fs')
const bodyParser = require('body-parser')
const jsonServer = require('json-server')
const jwt = require('jsonwebtoken')

const server = jsonServer.create()

const router = jsonServer.router('./db.json')

const db = JSON.parse(fs.readFileSync('./db.json', 'UTF-8'))

const middlewares = jsonServer.defaults();
const PORT = process.env.PORT || 3000;


server.use(middlewares);


server.use(jsonServer.defaults());
server.use(bodyParser.urlencoded({extended: true}))
server.use(bodyParser.json())

const SECRET_KEY = '123456789'
const expiresIn = '1h'

function createToken(payload) {
    return jwt.sign(
        payload, 
        SECRET_KEY, 
        {expiresIn})
}

function verifyToken(token) {
    return jwt.verify(
        token, 
        SECRET_KEY,  
        (err, decode) => decode !== undefined ?  decode : err)
}

function isAuthenticated({Email, Password}){
    return db.users.findIndex(user => user.Email === Email && user.Password === Password) !== -1
}

server.post('/register', (req, res) => {
  const {FirstName , LastName , Email, Password} = req.body;

  exist_user = db.users.findIndex(x => x.Email === Email)
  if(exist_user !== -1) {
    return res.status(401).json({
      status: 401,
      message: "Email already in use!",
    })
  }

  const new_user = {
    'id': db.users.length+1,
    FirstName,
    LastName,
    Email,
    Password
  }

  db.users.push(new_user);
  fs.writeFileSync('./db.json', JSON.stringify(db), () => {
    if (err) return console.log(err);
    console.log('writing to ' + fileName);
  })
  res.status(201).json({
    status: 201,
    message: "Success",
    data: new_user
  })
})

//login
server.post('/login', (req, res) => {
    const Email = req.body.Email
    const Password = req.body.Password

    if (isAuthenticated({Email, Password}) === false) {
      const status = 401
      const message = 'Incorrect Email or Password'
      res.status(status).json({status, message})
      return
    }
    const access_token = createToken({Email, Password})
    res.status(200).json({
      status: 200,
      message: "Success",
      data: {
        access_token
      }
    })
})

server.use('/auth',(req, res, next) => {
  if (req.headers.authorization == undefined || req.headers.authorization.split(' ')[0] !== 'Bearer') {
    const status = 401
    const message = 'Bad authorization header'
    res.status(status).json({status, message})
    return
  }
  try {
    let verifyTokenResult;
     verifyTokenResult = verifyToken(req.headers.authorization.split(' ')[1]);

     if (verifyTokenResult instanceof Error) {
       const status = 401
       const message = 'Error: access_token is not valid'
       res.status(status).json({status, message})
       return
     }
     next()
  } catch (err) {
    const status = 401
    const message = 'Token đã hết hạn'
    res.status(status).json({status, message})
  }
})


//view all users
server.get('/auth/users', (req, res) => {
  res.status(200).json({
    status: 200,
    data: {
      "users" : db.users
    }
  })
})

//Xem thông tin user theo Email
server.get('/auth/users/:Email', ((req, res)=> {
	const Email = req.params.Email;
 
	const exist_Email = db.users.findIndex(user =>  user.Email == Email)
	const result = db.users.filter(user =>  user.Email == Email)
	if (exist_Email !== -1)
	{
		const status = 200
		return res.status(status).json({status, result})
	} else {
    return res.status(401).json({
      status: 401,
      message: "Email is not found!!",
    })
}}))

//delete user by Email
server.delete('/auth/users/:Email', (req, res) => {
  const Email = req.params.Email

  const exist_Email = db.users.findIndex(user =>  user.Email == Email)
  if(exist_Email !== -1) {
    db.users.splice(exist_Email, 1);

    fs.writeFileSync('./db.json', JSON.stringify(db), () => {
      if (err) return console.log(err);
      console.log('writing to ' + fileName);
    })

    return res.status(204).json({
      status: 204,
      message: "Success",
    })
  } else {
    return res.status(401).json({
      status: 401,
      message: "Email is not found!!",
    })
  }

})

//view all orders
server.get('/auth/orders', (req, res) => {
  res.status(200).json({
    status: 200,
    message: "Success",
    data: {
      "orders" : db.orders
    }
  })
})

//view order by id
server.get('/auth/orders/:id', (req, res) => {
  const order_id = req.params.id

  const exist_order = db.orders.findIndex(order => order.id == order_id)
  if(exist_order !== -1) {
      res.status(200).json({
            status: 200,
            data: {
              'orders': db.orders[exist_order]
            }
          })
    } else {
      return res.status(401).json({
        status: 401,
        message: "Order not found!!",
      })
    }
  })

//add new order
server.post('/auth/orders', (req, res) => {
  const {ProductId, Name,Phone,Address,Payment,Quantity} = req.body
  const exist_product_id = db.items.findIndex(item => item.id === ProductId)
  if(exist_product_id === -1) {
    return res.status(401).json({
      status: 401,
      message: "Clothes not found!!",
    })
  }

  const order_product = db.items[exist_product_id]
  // console.log(order_product)
  if(order_product.quantity>0) {
    const new_order = {
      'id': db.orders.length+1,
      ProductId,
      Name,
      Phone,
      Address,
      Payment,
      Quantity,
      "timestamp": new Date().getTime()
    }
  
    db.orders.push(new_order);
    fs.writeFileSync('./db.json', JSON.stringify(db), () => {
      if (err) return console.log(err);
      console.log('writing to ' + fileName);
    })
    return res.status(200).json({
      status: 200,
      message: "Success",
      data: new_order
    })
  } else {
    return res.status(401).json({
      status: 401,
      message: "Clothes is out of stock!!",
    })
  }
})

//delete order by id
server.delete('/auth/orders/:id', (req, res) => {
  const order_id = req.params.id

  const exist_order = db.orders.findIndex(order => order.id == order_id)
  if(exist_order !== -1) {
    db.orders.splice(exist_order, 1);

    fs.writeFileSync('./db.json', JSON.stringify(db), () => {
      if (err) return console.log(err);
      console.log('writing to ' + fileName);
    })

    return res.status(204).json({
      status: 204,
      message: "Success",
    })
  } else {
    return res.status(401).json({
      status: 401,
      message: "Order not found!!",
    })
  }

})

//update username
server.patch('/auth/orders/:id', (req, res) => {
  const order_id = req.params.id
  const Name = req.body.Name

  const exist_order = db.orders.findIndex(order => order.id == order_id)
  if(exist_order !== -1) {
    db.orders[exist_order].Name = Name

    fs.writeFileSync('./db.json', JSON.stringify(db), () => {
      if (err) return console.log(err);
      console.log('writing to ' + fileName);
    })

    res.status(200).json({
      status: 200,
      message: "Success",
      data: {
        'orders': db.orders[exist_order]
      }
    })
  } else {
    res.status(401).json({
      status: 401,
      message: "Order not found!!",
    })
  }

})

server.use(router)

server.listen(PORT, () => {
  console.log('Run Auth API Server')
})