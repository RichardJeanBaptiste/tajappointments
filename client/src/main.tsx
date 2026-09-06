import { StrictMode } from 'react';
import { createRoot } from 'react-dom/client';
import { BrowserRouter, Routes, Route } from 'react-router';
import './index.css';
import App from './App.tsx';
import Dashboard from './components/Dashboard.tsx';
import { AuthProvider } from './components/AuthContext.tsx';
import { ProtectedRoute } from './components/ProtectedRoute.tsx';

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <AuthProvider>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<App />}/>

          {/****************** Protected Routes ****************/}
          <Route element={<ProtectedRoute />}>
            <Route path="/dashboard" element={<Dashboard />}/>
          </Route>
        </Routes>
      </BrowserRouter>
    </AuthProvider>
  </StrictMode>,
)
