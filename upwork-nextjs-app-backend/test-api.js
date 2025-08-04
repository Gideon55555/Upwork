const axios = require('axios');

const API_BASE_URL = 'http://localhost:8080/api';

// Test API endpoints
async function testAPI() {
  console.log('🧪 Testing Mover CRM API...\n');

  try {
    // Test 1: Health check (if available)
    console.log('1. Testing server availability...');
    try {
      const response = await axios.get(`${API_BASE_URL}/auth/me`);
      console.log('✅ Server is running');
    } catch (error) {
      if (error.response?.status === 401) {
        console.log('✅ Server is running (authentication required)');
      } else {
        console.log('❌ Server is not running or not accessible');
        return;
      }
    }

    // Test 2: Register a test user
    console.log('\n2. Testing user registration...');
    try {
      const registerResponse = await axios.post(`${API_BASE_URL}/auth/register`, {
        username: 'testuser',
        password: 'testpass123',
        email: 'test@movercrm.com',
        firstName: 'Test',
        lastName: 'User',
        role: 'ADMIN'
      });
      console.log('✅ User registration successful');
    } catch (error) {
      if (error.response?.status === 400 && error.response.data?.message?.includes('already exists')) {
        console.log('✅ User already exists (expected)');
      } else {
        console.log('❌ User registration failed:', error.response?.data?.message || error.message);
      }
    }

    // Test 3: Login
    console.log('\n3. Testing user login...');
    let token = '';
    try {
      const loginResponse = await axios.post(`${API_BASE_URL}/auth/login`, {
        username: 'testuser',
        password: 'testpass123'
      });
      token = loginResponse.data.token;
      console.log('✅ Login successful');
    } catch (error) {
      console.log('❌ Login failed:', error.response?.data?.message || error.message);
      return;
    }

    // Test 4: Get current user
    console.log('\n4. Testing get current user...');
    try {
      const userResponse = await axios.get(`${API_BASE_URL}/auth/me`, {
        headers: { Authorization: `Bearer ${token}` }
      });
      console.log('✅ Get current user successful:', userResponse.data.username);
    } catch (error) {
      console.log('❌ Get current user failed:', error.response?.data?.message || error.message);
    }

    // Test 5: Create a test client
    console.log('\n5. Testing client creation...');
    try {
      const clientResponse = await axios.post(`${API_BASE_URL}/clients`, {
        name: 'Test Client',
        phone: '+972-50-123-4567',
        email: 'client@test.com',
        address: 'Test Address, Tel Aviv'
      }, {
        headers: { Authorization: `Bearer ${token}` }
      });
      console.log('✅ Client creation successful:', clientResponse.data.name);
    } catch (error) {
      console.log('❌ Client creation failed:', error.response?.data?.message || error.message);
    }

    // Test 6: Get all clients
    console.log('\n6. Testing get all clients...');
    try {
      const clientsResponse = await axios.get(`${API_BASE_URL}/clients`, {
        headers: { Authorization: `Bearer ${token}` }
      });
      console.log(`✅ Get clients successful: ${clientsResponse.data.length} clients found`);
    } catch (error) {
      console.log('❌ Get clients failed:', error.response?.data?.message || error.message);
    }

    // Test 7: Create a test order
    console.log('\n7. Testing order creation...');
    try {
      const orderResponse = await axios.post(`${API_BASE_URL}/orders`, {
        orderNumber: 'ORD-001',
        service: 'Квартирный переезд',
        status: 'PENDING',
        date: '2025-08-15',
        time: '09:00',
        price: 1500,
        priority: 'MEDIUM',
        notes: 'Test order'
      }, {
        headers: { Authorization: `Bearer ${token}` }
      });
      console.log('✅ Order creation successful:', orderResponse.data.orderNumber);
    } catch (error) {
      console.log('❌ Order creation failed:', error.response?.data?.message || error.message);
    }

    // Test 8: Get all orders
    console.log('\n8. Testing get all orders...');
    try {
      const ordersResponse = await axios.get(`${API_BASE_URL}/orders`, {
        headers: { Authorization: `Bearer ${token}` }
      });
      console.log(`✅ Get orders successful: ${ordersResponse.data.length} orders found`);
    } catch (error) {
      console.log('❌ Get orders failed:', error.response?.data?.message || error.message);
    }

    console.log('\n🎉 API testing completed!');

  } catch (error) {
    console.error('❌ Test failed:', error.message);
  }
}

// Run the test
testAPI(); 