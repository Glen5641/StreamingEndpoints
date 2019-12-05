package main

import (
	"fmt"
	"log"
	"net/http"
  "time"
  "math/rand"

	"github.com/gorilla/mux"
)

func homeLink(w http.ResponseWriter, r *http.Request) {
  flusher, ok := w.(http.Flusher)
  if !ok {
    panic("expected http.ResponseWriter to be an http.Flusher")
  }
  w.Header().Set("X-Content-Type-Options", "nosniff")
  for {
    fmt.Fprintf(w, "{ random : %d }\n", rand.Intn(100))
    flusher.Flush() // Trigger "chunked" encoding and send a chunk...
    time.Sleep(time.Second)
  }
}

func main() {
	router := mux.NewRouter().StrictSlash(true)
	router.HandleFunc("/", homeLink)
	log.Fatal(http.ListenAndServe(":8000", router))
}
