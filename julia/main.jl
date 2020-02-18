#!/usr/local/bin/jl

include("./phone.jl")
import .phone: add, parse

add("86","CHN")
add("86186","CU")
add("86180","CT")
add("1","USA")
add("1186","USCU")
add("1180","USCT")
add("11809","US-9")
println(parse("8610801111"))
println(parse("861801111"))
println(parse("12801111"))
println(parse("11801111"))
println(parse("118097777"))
