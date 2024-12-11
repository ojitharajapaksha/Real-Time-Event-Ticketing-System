import React, { useState } from 'react';
import { configureSystem } from '../api';

// Componenets for configuring the system with form inputs
const ConfigurationForm = ({ onConfigured }) => {
  // Condition for error messages and form values
  const [config, setConfig] = useState({
    totalTickets: '',
    ticketReleaseRate: '',
    customerRetrievalRate: '',
    maxTicketCapacity: '',
  });
  const [error, setError] = useState(null);

  // Handler for form field changes
  const handleChange = (e) => {
    const { name, value } = e.target;
    if (parseInt(value, 10) < 0) {
      // Checking the inputs are positive
      setError(`${name} must be a positive number.`);
      return;
    }
    setError(null); 
    // Clear error
    setConfig({ ...config, [name]: value });
  };

  // Handler for submission
  const handleSubmit = async (e) => {
    e.preventDefault();
    const { totalTickets, maxTicketCapacity } = config;

    // Validation for maximum ticket capacity
    if (parseInt(maxTicketCapacity, 10) < parseInt(totalTickets, 10)) {
      setError("Error: Maximum ticket capacity cannot be less than the total number of tickets.");
      return;
    }

    try {
      await configureSystem(config);
      // Call API to configure the system
      alert('System configured successfully!');
      onConfigured(); 
    } catch (err) {
      // Notify to the components
      const errorMessage = err.response?.data?.message || 'Failed to configure the system. Please try again.';
      setError(errorMessage);
    }
  };

  return (
    <form className="form" onSubmit={handleSubmit}>
      <h2>Configure the System</h2>
      {error && <p className="error">{error}</p>}
      {/*Input fields for system configuration*/}
      <label For="totalTickets">Enter the total number of tickets:</label>
      <input
        type="number"
        name="totalTickets"
        placeholder="Total number of tickets"
        value={config.totalTickets}
        onChange={handleChange}
        required
        min="1"
      />
      <label For="totalTickets">Enter the ticket release rate per second:</label>
      <input
        type="number"
        name="ticketReleaseRate"
        placeholder="Ticket release rate per second"
        value={config.ticketReleaseRate}
        onChange={handleChange}
        required
        min="1"
      />
      <label For="totalTickets">Enter the customer retrieval rate per second:</label>
      <input
        type="number"
        name="customerRetrievalRate"
        placeholder="Customer retrieval rate per second"
        value={config.customerRetrievalRate}
        onChange={handleChange}
        required
        min="1"
      />
      <label For="totalTickets">Enter the maximum ticket capacity:</label>
      <input
        type="number"
        name="maxTicketCapacity"
        placeholder="Maximum ticket capacity"
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
