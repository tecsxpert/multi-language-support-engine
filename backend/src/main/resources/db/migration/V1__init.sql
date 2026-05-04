-- Users table
CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL DEFAULT 'USER',
    created_at TIMESTAMP NOT NULL DEFAULT NOW()
);

-- Translations table
CREATE TABLE IF NOT EXISTS translations (
    id BIGSERIAL PRIMARY KEY,
    source_text TEXT NOT NULL,
    translated_text TEXT NOT NULL,
    source_language VARCHAR(10) NOT NULL,
    target_language VARCHAR(10) NOT NULL,
    status VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

-- Records table (teammate feature)
CREATE TABLE IF NOT EXISTS records (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    language VARCHAR(50) NOT NULL,
    translated_text TEXT,
    status VARCHAR(50) DEFAULT 'NEW',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Indexes for translations
CREATE INDEX IF NOT EXISTS idx_translations_source_language
    ON translations(source_language);

CREATE INDEX IF NOT EXISTS idx_translations_target_language
    ON translations(target_language);

CREATE INDEX IF NOT EXISTS idx_translations_status
    ON translations(status);

-- Index for users
CREATE INDEX IF NOT EXISTS idx_users_email
    ON users(email);

-- Indexes for records
CREATE INDEX IF NOT EXISTS idx_language
    ON records(language);

CREATE INDEX IF NOT EXISTS idx_status
    ON records(status);