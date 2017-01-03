# revenj
POST /Domain.svc/submit/security.RegisterUser HTTP/1.1
Authorization: Basic YWRtaW46YWRtaW4=
Host: localhost:8080
Cache-Control: no-cache
Postman-Token: e0427c12-3f59-d251-395a-ebbe14fbd1d6

{
  "username": "Table4",
  "password": "YWJj"
}
#password is base64

#login
curl -X POST -H "X-Requested-With: XMLHttpRequest" -H "Content-Type: application/json" -H "Cache-Control: no-cache" -d '{"username": "admin","password": "admin"}' "http://localhost:8080/api/auth/login"
#about
curl -X GET -H "X-Authorization: Bearer token" -H "Cache-Control: no-cache" "http://localhost:8080/api/me
#post event
curl -X POST -H "X-Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsInNjb3BlcyI6WyJBRE1JTiJdLCJpc3MiOiJodHRwOi8vc3ZsYWRhLmNvbSIsImlhdCI6MTQ4MzQzMjgyMywiZXhwIjoxNDgzNDMzNzIzfQ.GavvoN7sFNJEc7E-W2XnOolmr7kP5TMoqRgKRd2W7myi_AsdKsbCFW_ZZI2qKosbYkRBbpOLSu-_V4GBE724Uw" -H "X-Requested-With: XMLHttpRequest" -H "Content-Type: application/json" -H "Cache-Control: no-cache" -d '{"username": "Table10","password": "YWJj"}' "http://localhost:8080/Domain.svc/submit/security.RegisterUser"
