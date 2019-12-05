from django.http.response import StreamingHttpResponse
import time
from random import random

def rng(msg):
    time.sleep(1)
    data = {}
    data['random'] = random()
    return data


def iterator():
    for i in range(10000):
        yield rng('iteration ' + str(i))

def index(request):
    stream = iterator()
    response = StreamingHttpResponse(stream, status=200, content_type='application/json')
    response['Cache-Control'] = 'no-cache'
    return response
