#!/opt/bin/deno --allow-net

import { PhoneNo } from "./phone.ts"

async function test_phone(): Promise<object> {
	const phone = new PhoneNo()
	phone.add('86', 'CHN')
	phone.add('86755', 'SZ')
	phone.add('8675521', 'NS')

	let res = {
		8611111111: phone.parse('8611111111'),
		8675511111111: phone.parse('8675511111111'),
		86755211111111: phone.parse('86755211111111'),
	}

	return res
}

async function main() {
	let res = await test_phone()
	console.log(res)
}

main()
