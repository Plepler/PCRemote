from twisted.internet import reactor, protocol

PORT = 9000

class RequestCodes():
	PLAYPAUSE = 1 # Pause / Resume
	NEXTTRACK = 2
	PREVTRACK = 3
	REWIND = 4

class MyServer(protocol.Protocol):
    
	def dataReceived(self, data):
		
		# Check if data is code
		if data == None:
			return None

		try:
			code = int(data[0].decode())
		except Exception as e:
			print(e)
			return None
		
		self.factory.handleRequest(code)



	def connectionMade(self):
		print("Someone connected")

	def connectionLost(self, reason):
		print(reason.getErrorMessage())