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

curl -X POST -H "X-Requested-With: XMLHttpRequest" -H "Content-Type: application/json" -H "Cache-Control: no-cache" -d '{"username": "user","password": "user"}' "http://localhost:8080/api/auth/login"
