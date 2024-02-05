import React from 'react';
import './App.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import { AddUser } from './Pages/AddUserPage';
import { AddPaUser } from './Pages/AddPaUserPage';
import { FmProvider } from 'fm-library';
import { DeleteUser } from './Pages/DeleteUserPage';
import { Toaster } from 'react-hot-toast';
import { InitTransaction } from './Pages/InitTransactionPage';
import { TransactionPage } from './Pages/TransactionPage';
function App() {
  return (
    <FmProvider>
      <div>
        <Toaster
          position="top-center"/>
      </div>
      <BrowserRouter>
        <Routes>
          <Route path='/' element={<InitTransaction />}></Route>
          <Route path='/addUser' element={<AddUser />}></Route>
          <Route path='/addPaUser' element={<AddPaUser />}></Route>
          <Route path='/deleteUser' element={<DeleteUser />}></Route>
          <Route path='/transaction' element={<TransactionPage />}></Route>
        </Routes>
      </BrowserRouter>
    </FmProvider>
  );
}

export default App;
