package main

import (
	"fmt"
	"phone"
)

func main() {
	parser := phone.Parser{}
	parser.Add("86", "CHN")
	parser.Add("86755", "SZ")
	parser.Add("8675580", "YT")
	fmt.Println(parser.Parse("8612345678"))
	fmt.Println(parser.Parse("8675512345678"))
	fmt.Println(parser.Parse("8675580345678"))
}
