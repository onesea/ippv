import 'package:dart/phone.dart' as phone;

void print_phone(nr) => print('${nr.cc} ${nr.ld} ${nr.ac} ${nr.iso}');

void main(List<String> arguments) {
  phone.add('1', 'us');
  phone.add('120', 'ca');
  phone.add('120300', 'mv');

  print_phone(phone.match('1211'));
  print_phone(phone.match('1201'));
  print_phone(phone.match('1203001'));
  print_phone(phone.match('1203011'));
}
