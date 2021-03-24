from twisted.internet import protocol
from Server import MyServer, RequestCodes
import pyautogui
import 
class MyFactory(protocol.Factory):
	
	protocol = MyServer

	def __init__(self):
		pass

	#############TRACK CONTROL############

	def playpause(self):
		pyautogui.press('playpause')

	def prevtrack(self):
		pyautogui.press('prevtrack')

	def nexttrack(self):
		pyautogui.press('nexttrack')

	def rewind(self):
		pyautogui.press('prevtrack')
		pyautogui.press('nexttrack')

	#############TRACK CONTROL############



	def handleRequest(self, code):
		
		if RequestCodes.NEXTTRACK == code:
			self.nexttrack()

		if RequestCodes.PREVTRACK == code:
			self.prevtrack()

		if RequestCodes.PLAYPAUSE == code:
			self.playpause()

		if RequestCodes.REWIND == code:
			self.rewind()
