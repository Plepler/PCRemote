from twisted.internet import reactor, protocol

PORT = 9000

class MyServer(protocol.Protocol):
    
	def dataReceived(self, data):
		pass

	def connectionMade(self):
		pass

	def connectionLost(self, reason):
		print(reason.getErrorMessage())