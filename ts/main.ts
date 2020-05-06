// deno run --allow-net main.ts

import { serve } from "https://deno.land/std/http/server.ts";
import { PhoneNo } from "./phone.ts"

function test_phone(): object {
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
	let res = test_phone()
	console.log(res)
	const s = serve({ port: 8000 })
	console.log("http://localhost:8000/")
	const msg = new TextEncoder().encode("Hello World")
	for await (const req of s) {
		req.respond({ body: JSON.stringify(res) + msg })
	}
}

main()
