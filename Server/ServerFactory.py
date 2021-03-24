from twisted.internet import protocol
from Server import MyServer

class MyFactory(protocol.Factory):
	
	protocol = MyServer

	def __init__(self):
		pass
