//  deno.exe --allow-net .\main.ts
import { serve } from "https://deno.land/std/http/server.ts";
import { PhoneNo } from "./phone.ts"

const phone = new PhoneNo()
phone.add('86', 'CHN')
phone.add('86755', 'SZ')
phone.add('8675521', 'NS')
console.log(phone.parse('8611111111'))
console.log(phone.parse('8675511111111'))
console.log(phone.parse('86755211111111'))

async function main() {
	const body = new TextEncoder().encode("Hello World\n");
	const s = serve({ port: 8000 });
	console.log("http://localhost:8000/");
	for await (const req of s) {
	  req.respond({ body });
	}
}

main()
