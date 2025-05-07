import React, { useState } from 'react';
import axios from 'axios';
import './App.css';

function App() {
  const [query, setQuery] = useState('');
  const [suggestions, setSuggestions] = useState([]);
  const [results, setResults] = useState([]);

  const handleInputChange = async (e) => {
    const value = e.target.value;
    setQuery(value);
    if (value.length > 0) {
      const response = await axios.get(`http://localhost:8080/autocomplete?prefix=${value}`);
      setSuggestions(response.data);
    } else {
      setSuggestions([]);
    }
  };

  const handleSearch = async () => {
    const response = await axios.get(`http://localhost:8080/search?query=${query}`);
    setResults(response.data);
    setSuggestions([]);
  };

  return (
    <div className="App">
      <h1>Search Engine</h1>
      <input
        type="text"
        value={query}
        onChange={handleInputChange}
        placeholder="Enter search query"
      />
      <button onClick={handleSearch}>Search</button>
      {suggestions.length > 0 && (
        <ul>
          {suggestions.map((suggestion, index) => (
            <li key={index}>{suggestion}</li>
          ))}
        </ul>
      )}
      {results.map((result) => (
        <div key={result.id}>
          <h3>{result.title}</h3>
          <p>{result.description}</p>
        </div>
      ))}
    </div>
  );
}

export default App;
