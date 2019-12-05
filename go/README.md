install go and dep
sudo snap install go --classic
export GOPATH=directory
sudo apt-get install go-dep
mkdir -p $GOPATH/src/github.com/me/endpoint
cd $GOPATH/src/github.com/me/endpoint
dep init
go build
./endpoint
go get -u github.com/gorilla/mux
