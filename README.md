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

curl -X GET -H "X-Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwic2NvcGVzIjpbIkdPRE1PREUiXSwiaXNzIjoiaHR0cDovL3N2bGFkYS5jb20iLCJpYXQiOjE0ODMzMjgwNjUsImV4cCI6MTQ4MzMyODk2NX0.c7UpiYTCi0uhxk0OQK0Y3VN_cbr9u0kJxr6WjJhEb8X_69ELkDwVWT_dObKo5dANXnxqJ8pwvuk6pNzl04PyQg" -H "Cache-Control: no-cache" "http://localhost:8080/api/me

curl -X GET -H "X-Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwic2NvcGVzIjpbIkdPRE1PREUiXSwiaXNzIjoiaHR0cDovL3N2bGFkYS5jb20iLCJpYXQiOjE0ODMzMzExMjksImV4cCI6MTQ4MzMzMjAyOX0.SDYNF3SxHloR0E-_vhidR_Yd10VLbMKzuhKELDRoRw4hZ7WNW-MbUlKrIi6bcZsY_pgnUETfzM35InIqcWhing" -H "Cache-Control: no-cache" "http://localhost:8080/api/me