import _ from 'lodash';
import PhoneNo from "./phone"
//const PhoneNo = require("./phone")

function component() {
  const element = document.createElement('div');

  element.innerHTML = _.join(['ippv', 'demo'], ' ');

  return element;
}

function testPhone () {
    let phone = new PhoneNo("latest")
    
    phone.add('80','I80')
    phone.add('8011','I8011')
    phone.add('801122','I801122')
    
    //console.log(phone.parse('80223344'))
    //console.log(phone.parse('8011723344'))
    const element = document.createElement('div');
    element.innerHTML = "801122723344 => " + JSON.stringify(phone.parse('801122723344'));
    return element;
}

document.body.appendChild(component());
document.body.appendChild(testPhone());
