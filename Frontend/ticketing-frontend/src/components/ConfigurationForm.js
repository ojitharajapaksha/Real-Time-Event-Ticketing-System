import React, { useState } from 'react';
import { configureSystem } from '../api';

const ConfigurationForm = ({ onConfigured }) => {
  const [config, setConfig] = useState({
    totalTickets: '',
    ticketReleaseRate: '',
    customerRetrievalRate: '',
    maxTicketCapacity: '',
  });
  const [error, setError] = useState(null);

  const handleChange = (e) => {
    const { name, value } = e.target;
    if (parseInt(value, 10) < 0) {
      setError(`${name} must be a positive number.`);
      return;
    }
    setError(null); 
    setConfig({ ...config, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const { totalTickets, maxTicketCapacity } = config;

    if (parseInt(maxTicketCapacity, 10) < parseInt(totalTickets, 10)) {
      setError("Error: Maximum ticket capacity cannot be less than the total number of tickets.");
      return;
    }

    try {
      await configureSystem(config);
      alert('System configured successfully!');
      onConfigured(); 
    } catch (err) {
      const errorMessage = err.response?.data?.message || 'Failed to configure the system. Please try again.';
      setError(errorMessage);
    }
  };

  return (
    <form className="form" onSubmit={handleSubmit}>
      <h2>Configure the System</h2>
      {error && <p className="error">{error}</p>}
      <input
        type="number"
        name="totalTickets"
        placeholder="Total Tickets"
        value={config.totalTickets}
        onChange={handleChange}
        required
        min="1"
      />
      <input
        type="number"
        name="ticketReleaseRate"
        placeholder="Ticket Release Rate"
        value={config.ticketReleaseRate}
        onChange={handleChange}
        required
        min="1"
      />
      <input
        type="number"
        name="customerRetrievalRate"
        placeholder="Customer Retrieval Rate"
        value={config.customerRetrievalRate}
        onChange={handleChange}
        required
        min="1"
      />
      <input
        type="number"
        name="maxTicketCapacity"
        placeholder="Max Ticket Capacity"
        value={config.maxTicketCapacity}
        onChange={handleChange}
        required
        min="1"
      />
      <button type="submit">Submit Configuration</button>
    </form>
  );
};

export default ConfigurationForm;
