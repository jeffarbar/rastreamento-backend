CREATE DATABASE rastreamento;
CREATE USER user_rastreamento WITH PASSWORD 'Bianchini$01';
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO user_rastreamento;