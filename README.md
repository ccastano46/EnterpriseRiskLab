# Create the API
![Captura de pantalla 2026-10-14 a la(s) 11.00.16 a.m..png](docs/images/Captura%20de%20pantalla%202026-10-14%20a%20la%28s%29%2011.00.16%E2%80%AFa.m..png)

# Define the permissions
![Captura de pantalla 2026-10-14 a la(s) 11.00.50 a.m..png](docs/images/Captura%20de%20pantalla%202026-10-14%20a%20la%28s%29%2011.00.50%E2%80%AFa.m..png)

# Create and configure browser client

![Captura de pantalla 2026-10-14 a la(s) 11.02.49 a.m..png](docs/images/Captura%20de%20pantalla%202026-10-14%20a%20la%28s%29%2011.02.49%E2%80%AFa.m..png)

# Grant the SPA access to the API

![Captura de pantalla 2026-10-14 a la(s) 11.05.29 a.m..png](docs/images/Captura%20de%20pantalla%202026-10-14%20a%20la%28s%29%2011.05.29%E2%80%AFa.m..png)

# Build the Spring Boot backend

![Captura de pantalla 2026-10-14 a la(s) 11.53.11 a.m..png](docs/images/Captura%20de%20pantalla%202026-10-14%20a%20la%28s%29%2011.53.11%E2%80%AFa.m..png)

# Build the React frontend

![Captura de pantalla 2026-10-14 a la(s) 12.52.56 p.m..png](docs/images/Captura%20de%20pantalla%202026-10-14%20a%20la%28s%29%2012.52.56%E2%80%AFp.m..png)

![Captura de pantalla 2026-10-14 a la(s) 12.53.30 p.m..png](docs/images/Captura%20de%20pantalla%202026-10-14%20a%20la%28s%29%2012.53.30%E2%80%AFp.m..png)

![Captura de pantalla 2026-10-14 a la(s) 12.54.06 p.m..png](docs/images/Captura%20de%20pantalla%202026-10-14%20a%20la%28s%29%2012.54.06%E2%80%AFp.m..png)

![Captura de pantalla 2026-10-14 a la(s) 12.54.38 p.m..png](docs/images/Captura%20de%20pantalla%202026-10-14%20a%20la%28s%29%2012.54.38%E2%80%AFp.m..png)

# Prepare the server and Duck DNS

Creamos la instancia

![Captura de pantalla 2026-10-14 a la(s) 4.46.17 p.m..png](docs/images/Captura%20de%20pantalla%202026-10-14%20a%20la%28s%29%204.46.17%E2%80%AFp.m..png)

![Captura de pantalla 2026-10-14 a la(s) 4.47.32 p.m..png](docs/images/Captura%20de%20pantalla%202026-10-14%20a%20la%28s%29%204.47.32%E2%80%AFp.m..png)

Cambiamos el inbound traffic del security group para que acepte trafico en los puertos 80 y 443

![Captura de pantalla 2026-10-14 a la(s) 4.52.20 p.m..png](docs/images/Captura%20de%20pantalla%202026-10-14%20a%20la%28s%29%204.52.20%E2%80%AFp.m..png)

We connect to the ec2 inastance and install the packages for apache, certbot and openjdk-21-jre

Ahora, conseguimos la IP publica de nuestra EC2 instance y construimos nuestro dominio en duckdns

![Captura de pantalla 2026-10-14 a la(s) 5.29.33 p.m..png](docs/images/Captura%20de%20pantalla%202026-10-14%20a%20la%28s%29%205.29.33%E2%80%AFp.m..png)

comprombamos que el dns responde con la ip publica de la instancia
![Captura de pantalla 2026-10-14 a la(s) 5.33.00 p.m..png](docs/images/Captura%20de%20pantalla%202026-10-14%20a%20la%28s%29%205.33.00%E2%80%AFp.m..png)

Luego, compilamos tanto el backend como el frontend y lo subimos a la instancia

Bash

npm run build
mvn clean package


Transferimos los archivos con

scp -i /ruta/a/tu-key.pem target/EnterpriseRiskLab-1.0-SNAPSHOT.jar ubuntu@TU_IP_PUBLICA:~/

scp -i /ruta/a/tu-key.pem -r dist ubuntu@TU_IP_PUBLICA:~/frontend-dist
![Captura de pantalla 2026-10-14 a la(s) 5.54.04 p.m..png](docs/images/Captura%20de%20pantalla%202026-10-14%20a%20la%28s%29%205.54.04%E2%80%AFp.m..png)

# Deploy the Backend
Movemos el jar a.....

![Captura de pantalla 2026-10-14 a la(s) 6.08.59 p.m..png](docs/images/Captura%20de%20pantalla%202026-10-14%20a%20la%28s%29%206.08.59%E2%80%AFp.m..png)

creamos usuario y damos permisos

![Captura de pantalla 2026-10-14 a la(s) 6.13.13 p.m..png](docs/images/Captura%20de%20pantalla%202026-10-14%20a%20la%28s%29%206.13.13%E2%80%AFp.m..png)


Creamo el archivo de servicio

sudo nano /etc/systemd/system/enterprise-risk-api.service

![Captura de pantalla 2026-10-14 a la(s) 6.15.42 p.m..png](docs/images/Captura%20de%20pantalla%202026-10-14%20a%20la%28s%29%206.15.42%E2%80%AFp.m..png)


Probamos que este corriendo el servicio

![Captura de pantalla 2026-10-14 a la(s) 6.20.33 p.m..png](docs/images/Captura%20de%20pantalla%202026-10-14%20a%20la%28s%29%206.20.33%E2%80%AFp.m..png)

# Apache

Copiamos la carpeta frontend-dist a la carpeta de apache

sudo mkdir -p /var/www/enterprise-risk-frontend

sudo cp -r ~/frontend-dist/* /var/www/enterprise-risk-frontend/

Habilita los módulos necesarios

sudo a2enmod ssl proxy proxy_http rewrite headers

Crea el archivo de configuración

sudo nano /etc/apache2/sites-available/enterprise-risk.conf

![Captura de pantalla 2026-10-14 a la(s) 6.33.59 p.m..png](docs/images/Captura%20de%20pantalla%202026-10-14%20a%20la%28s%29%206.33.59%E2%80%AFp.m..png)

Habilitamos el sitio y pedimos certificados

sudo a2ensite enterprise-risk.conf
sudo apachectl configtest
sudo systemctl reload apache2
sudo certbot --apache -d tu-subdominio.duckdns.org

![Captura de pantalla 2026-10-14 a la(s) 6.37.34 p.m..png](docs/images/Captura%20de%20pantalla%202026-10-14%20a%20la%28s%29%206.37.34%E2%80%AFp.m..png)

Verificar que la renovacion automatica funcion

sudo certbot renew --dry-run

Probamos que todo funciona

![Captura de pantalla 2026-10-14 a la(s) 6.39.16 p.m..png](docs/images/Captura%20de%20pantalla%202026-10-14%20a%20la%28s%29%206.39.16%E2%80%AFp.m..png)


![Captura de pantalla 2026-10-14 a la(s) 12.54.38 p.m..png](docs/images/Captura%20de%20pantalla%202026-10-14%20a%20la%28s%29%2012.54.38%E2%80%AFp.m..png)