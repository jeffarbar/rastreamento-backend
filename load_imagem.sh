docker build -t jeffersonfarias/boris-backend:1.0.2  -f Dockerfile .
docker login --username=jeffersonfarias
docker push jeffersonfarias/boris-backend:1.0.2
