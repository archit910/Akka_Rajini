import smtplib
import mimetypes
from email.mime.multipart import MIMEMultipart
from email.mime.base import MIMEBase
from email.mime.text import MIMEText
from email.mime.image import MIMEImage
from email.utils import COMMASPACE, formatdate
from email import encoders
from django.http import HttpResponse
import os
def fun(r):

	#filePath = "is_synopsis.PDF"        #your file name
	filePath="%r"%r.GET['q']
	x=os.path.dirname(os.path.abspath(__file__))
	print(x)
	reply=""
	filePath=filePath[1:-1]
	#filePath="C:\\Users\\prashantkumar\\Documents\\"+
	filePath=x+"\\"+filePath
	print(filePath)
	From = 'messi040895@gmail.com'
	To = 'prashantk4081995@gmail.com'

	msg = MIMEMultipart()
	msg['From'] = From
	msg['To'] = To
	msg['Date'] = formatdate(localtime=True)
	msg['Subject'] = 'Sample subject image'

	msg.attach(MIMEText('Sample message'))

	try:
		smtp = smtplib.SMTP('smtp.gmail.com',587)
		smtp.starttls()
		smtp.login('messi040895@gmail.com', 'pk@191195')
	except:
		i = 1
	else:
		i = 0

	if i == 0:
		ctype, encoding = mimetypes.guess_type(filePath)
		if ctype is None or encoding is not None:
			# No guess could be made, or the file is encoded (compressed), so
			# use a generic bag-of-bits type.
			ctype = 'application/octet-stream'
		maintype, subtype = ctype.split('/', 1)
		if maintype == 'text':
			fp = open(filePath)
			# Note: we should handle calculating the charset
			part = MIMEText(fp.read(), _subtype=subtype)
			fp.close()
		elif maintype == 'image':
			fp = open(filePath, 'rb')
			part = MIMEImage(fp.read(), _subtype=subtype)
			fp.close()
		elif maintype == 'audio':
			fp = open(filePath, 'rb')
			part = MIMEAudio(fp.read(), _subtype=subtype)
			fp.close()
		else:
			fp = open(filePath, 'rb')
			part = MIMEBase(maintype, subtype)
			part.set_payload(fp.read())
			fp.close()
			# Encode the payload using Base64
			encoders.encode_base64(part)
		part.add_header('Content-Disposition', 'attachment; filename="%s"' % filePath)
		msg.attach(part)
		try:
			smtp.sendmail(From, To, msg.as_string())
		except:
			print ("Mail not sent")
			reply = "Mail not sent"
		else:
			print ("Mail sent")
			reply = "Mail sent"
		smtp.close()
	else:
		print ("Connection failed")
		reply ="Connection failed"
	return HttpResponse(reply)
