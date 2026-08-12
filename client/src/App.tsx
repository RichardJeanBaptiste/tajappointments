import { useState } from 'react'
import { Button, Form,  } from 'react-bootstrap';
import './App.css'

function App() {
  
  const [ loginForm, setLoginForm ] = useState({
    email: '',
    password: ''
  });

  const handleLoginFormChange = (e: React.ChangeEvent<any>, name: string) => {

      const value = e.target.value;

      setLoginForm((prev) => ({
        ...prev,
        [name]: value
      }));
  }

  const handleLoginSubmit = (e) => {
    console.log(loginForm);
    
    e.preventDefault();
    
    fetch("http://localhost:8080/auth/login", {
      method: 'POST',
      body: JSON.stringify(loginForm)
    }).then((response) => response.body)
    .then((data) => {
      console.log(data);
    })
  }

  return (
    <div>
        <Form style={{ width: '30%' }}>
          <Form.Group className='mb-3' controlId='loginEmail'>
              <Form.Label>Email: </Form.Label>
              <Form.Control type="email" placeholder='email' onChange={(e) => handleLoginFormChange(e,"email")} value={loginForm.email}/>
          </Form.Group>

          <Form.Group>
            <Form.Label>Password: </Form.Label>
            <Form.Control type='password' placeholder='password' onChange={(e) => handleLoginFormChange(e, "password")} value={loginForm.password}/>
          </Form.Group>

          <Button variant='primary' type="submit" onClick={handleLoginSubmit}>Submit</Button>
        </Form>
    </div>
  )
}

export default App
