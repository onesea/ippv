import 'package:dart/phone.dart' as phone;

void print_phone(nr,res) => print('${nr} -> ${res.cc} ${res.ld} ${res.ac} ${res.iso}');

void parse_phone(nr) {
	var res = phone.match(nr);
	print_phone(nr,res);
}

void main(List<String> arguments) {
	phone.add('1', 'us');
	phone.add('120', 'ca');
	phone.add('120300', 'mv');

	parse_phone('1211');
	parse_phone('1201');
	parse_phone('1203001');
	parse_phone('1203011');
}
