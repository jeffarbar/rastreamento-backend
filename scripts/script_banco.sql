CREATE DATABASE rastreamento;
CREATE USER user_rastreamento WITH PASSWORD 'Bianchini$01';
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO user_rastreamento;


#index

CREATE INDEX IF NOT EXISTS idx_email_senha_ativo ON usuario(email, senha , fg_ativo);

CREATE INDEX IF NOT EXISTS idx_email ON usuario(email);

CREATE INDEX IF NOT EXISTS idx_chave_troca_senha ON usuario(chave_troca_senha);
