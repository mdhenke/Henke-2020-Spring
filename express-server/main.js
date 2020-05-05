const express = require('express')

var mysql = require('mysql')
var connection = mysql.createConnection({
  host: 'localhost',
  port: 3309,
  user: 'root',
  password: 'Dkkkmh2135',
  database: 'skime'
})

connection.connect()

// connection.query('SELECT * from drivers', function (err, rows, fields) {
//   if (err) throw err

//   console.log('The solution is: ', rows[0].name)
// })

const app = express()
const port = 3000
app.use(express.static('public'))
app.get('/drivers', (req, res) => {
    var query = 'SELECT * from drivers'
    var resp = {}
    connection.query('SELECT * from drivers', function(err, rows, fields) {
        if (err) res.send({});
        res.send(rows)
    })
})
app.listen(port, () => console.log('SkiMe API listening at port 3000'))