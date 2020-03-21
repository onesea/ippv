GOPATH=$(shell pwd)
GO=go

all: main

main:
	@cd main && GOPATH=$(GOPATH) $(GO) install -v main
clean:
	@cd main && GOPATH=$(GOPATH) $(GO) clean -i main

.PHONY: main
