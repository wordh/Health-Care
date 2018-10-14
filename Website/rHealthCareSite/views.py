from django.shortcuts import render
import pyrebase

config = {
    'apiKey': "AIzaSyAvMaJjh_1whcsBO-cAJMIHH2maYgScN_A",
    'authDomain': "rohingya-health-care.firebaseapp.com",
    'databaseURL': "https://rohingya-health-care.firebaseio.com",
    'projectId': "rohingya-health-care",
    'storageBucket': "rohingya-health-care.appspot.com",
    'messagingSenderId': "508459477016"
}

firebase=pyrebase.initialize_app(config)

auth=firebase.auth()

def signIn(request):
    return render(request,"signIn.html")

def postsign(request):
    email=request.POST.get('email')
    password=request.POST.get('password')
    user=auth.sign_in_with_email_and_password(email,password)
    return render(request,"welcome.html",{"e":email})