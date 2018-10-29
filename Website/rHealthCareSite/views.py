from django.shortcuts import render
import pyrebase
from django.contrib import auth
config = {
    'apiKey': "AIzaSyAvMaJjh_1whcsBO-cAJMIHH2maYgScN_A",
    'authDomain': "rohingya-health-care.firebaseapp.com",
    'databaseURL': "https://rohingya-health-care.firebaseio.com",
    'projectId': "rohingya-health-care",
    'storageBucket': "rohingya-health-care.appspot.com",
    'messagingSenderId': "508459477016"
}

firebase=pyrebase.initialize_app(config)

authe=firebase.auth()
database=firebase.database()

def signIn(request):
    return render(request,"signIn.html")

def postsign(request):
    email=request.POST.get('email')
    password=request.POST.get('password')
    try:
        user=authe.sign_in_with_email_and_password(email,password)
    except:
        massage="Invalid Credentials"
        return render(request,"signIn.html",{"massage":massage})
    session_id=user['idToken']
    request.session['uid']=str(session_id)
    return render(request,"welcome.html",{"e":email})

def logout(request):
    auth.logout(request)
    return render(request,"signIn.html")

def createReport(request):
    return render(request,"createReport.html")

def post_create_report(request):
    import time
    from datetime import datetime, timezone
    import pytz

    tz=pytz.timezone('Asia/Dhaka')
    time_now_day=datetime.now(timezone.utc).astimezone(tz).day
    time_now_month = datetime.now(timezone.utc).astimezone(tz).month
    time_now_year = datetime.now(timezone.utc).astimezone(tz).year

    idtoken = request.session['uid']
    a = authe.get_account_info(idtoken)

    medicine=request.POST.get('medicine')
    practice=request.POST.get('practice')

    data={
        "medicine":medicine,
        "practice":practice
    }

    database.child('Patient').child('-LJ3QMmhxCG5dv1WtS3U').child('History').child(str(time_now_day)+"-"+str(time_now_month)+"-"+str(time_now_year)).set(data)

    return render(request, "welcome.html")

def check(request):

    idtoken = request.session['uid']
    a = authe.get_account_info(idtoken)

    timestamps=database.child('Patient').child('-LJ3QMmhxCG5dv1WtS3U').child('History').shallow().get().val()

    list_time=[]
    for i in timestamps:
        list_time.append(i)
    list_time.sort(reverse=True)

    medicine=[]
    practice=[]

    for i in list_time:
        wor=database.child('Patient').child('-LJ3QMmhxCG5dv1WtS3U').child('History').child(i).child('medicine').get().val()
        medicine.append(wor)
        prac=database.child('Patient').child('-LJ3QMmhxCG5dv1WtS3U').child('History').child(i).child('practice').get().val()
        practice.append(prac)

    comb_list = zip(list_time,medicine,practice)

    return render(request,'check.html',{'comb_list':comb_list})