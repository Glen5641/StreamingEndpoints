const express = require('express');
const app = express();
const port = 8000;

function setHead(res, streaming){
  res.status(200)
  if(streaming){
    res.set({
      'Connection' : 'keep-alive',
      'Content-Type' : 'application/json',
      'Cache-Control' : 'no-cache'
    })
  } else {
    res.set({
      'Content-Type' : 'application/json',
    })
  }
}

function setIndexInformation(){
  data = {}
  data.random = Math.floor(Math.random()*100);
  return data
}


function sendResponse(res, data, streaming) {
  if(streaming) res.write('data: ' + JSON.stringify(data) + '\n\n');
  else res.send('data: ' + JSON.stringify(data) + '\n\n');
}

app.get('/', function (req, res) {
  setHead(res, true);
  loop = setInterval(function(){sendResponse(res, setIndexInformation(), true); }, 1000);
  req.connection.on('close', function() { clearInterval(loop) });
})


app.listen(port, () =>
  console.log(`Endpoint listening on port ${port}!`));
