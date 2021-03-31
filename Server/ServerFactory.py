from twisted.internet import protocol
from Server import MyServer, RequestCodes
import os
import pyautogui
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

	def volup(self):
		pyautogui.press('volumeup')

	def voldown(self):
		pyautogui.press('volumedown')

	def shutdown(self):
		os.system('shutdown -s')

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

		if RequestCodes.VOLUP == code:
			self.volup()

		if RequestCodes.VOLDOWN == code:
			self.voldown()

		if RequestCodes.SHUTDOWN == code:
			self.shutdown()
