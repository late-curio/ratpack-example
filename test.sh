# shellcheck disable=SC2034
for i in {1..200}
do
  http localhost:5050 name==Kevyn
  sleep 1
  http localhost:5050 name==André
  sleep 1
  http localhost:5050 name==Jason
  sleep 1
  http localhost:5050 name==Brad
  sleep 1
  http localhost:5050 name==Oren
  sleep 1
done