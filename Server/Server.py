from twisted.internet import reactor, protocol

PORT = 9000
FIRST_ASCII_DIGIT = 48 # '0'

class RequestCodes():
	PLAYPAUSE = 1 # Pause / Resume
	NEXTTRACK = 2
	PREVTRACK = 3
	REWIND = 4
	VOLUP = 5
	VOLDOWN = 6
	SHUTDOWN = 7

class MyServer(protocol.Protocol):
    
	def dataReceived(self, data):
		
		# Check if data is code
		if data == None:
			return None

		try:
			code = data[0] - FIRST_ASCII_DIGIT
		except Exception as e:
			print(e)
			return None

		self.factory.handleRequest(code)



	def connectionMade(self):
		print("Someone connected")

	def connectionLost(self, reason):
		print(reason.getErrorMessage())