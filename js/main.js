const PhoneNo = require("./phone")

let phone = new PhoneNo("latest")

phone.add('80','I80')
phone.add('8011','I8011')
phone.add('801122','I801122')

console.log(phone.parse('80223344'))
console.log(phone.parse('8011723344'))
console.log(phone.parse('801122723344'))