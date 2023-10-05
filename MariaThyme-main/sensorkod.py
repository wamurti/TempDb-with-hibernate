import time
import os
import glob
import sys
import mariadb
import MySQLdb

try:
    db = mariadb.connect(host="hostURL",user="username",passwd="password",db="Databasename",port=3306
                     )

except mariadb.Error as e:
    print(f"Error connecting to MariaDB Platform: {e}")
    sys.exit(1)

cur = db.cursor()



os.system('modprobe w1-gpio')
os.system('modprobe w1-therm')

base_dir = '/sys/bus/w1/devices/'
device_folder = glob.glob(base_dir + '28*')[0]
device_file = device_folder + '/w1_slave'
def get_temp():
    file = open(device_file, 'r')
    lines = file.readlines()
    file.close()
    
    trimmed_data = lines[1].find('t=')
    
    if trimmed_data != -1:
        temp_string = lines[1][trimmed_data+2:]
        temp_c = float(temp_string)/1000.0
        return round(temp_c,1)
    
while 1:
    print('Temp = %.1f C' % get_temp())
    data = get_temp()
    sqData="Insert into frejsensor(temperatur) values ({d})".format(d=data)
    cur.execute(sqData)
    db.commit()
    db.close()
    exit()
        
    