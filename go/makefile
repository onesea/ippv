GOPATH=$(shell pwd)
GO=go

all: main

main:
	@GOPATH=$(GOPATH) cd main && $(GO) install -v main
clean:
	@GOPATH=$(GOPATH) cd main && $(GO) clean -i main

.PHONY: main
