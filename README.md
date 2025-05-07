# Search-Engine-with-Autocomplete

A web-based search engine with autocomplete, caching, and spell-check.

## Features
- Full-text search with Elasticsearch
- Autocomplete using a trie
- Caching with Redis
- Spell-check with Levenshtein distance

## Setup
1. Install Java, Node.js, Elasticsearch, Redis.
2. Run `mvn spring-boot:run` in `backend`.
3. Run `npm start` in `frontend`.

## Directory Structure
- `backend/`: Java/Spring Boot backend with Elasticsearch and Redis integration.
- `frontend/`: React frontend for search UI and autocomplete.
