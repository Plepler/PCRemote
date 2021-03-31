from twisted.internet import reactor
from ServerFactory import MyFactory
from Server import PORT


def main():

	factory = MyFactory()
	reactor.listenTCP(PORT, factory) # pylint: disable=no-member
	reactor.run() # pylint: disable=no-member

if __name__ == '__main__':
	main()