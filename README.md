# simpel-projek
test-multi modul

ignore gitlab ci/cd

docker-compose -f docker-compose.yml -f docker-compose.product.yml up -d
docker-compose -f docker-compose.yml -f docker-compose.transaksi.yml up -d
