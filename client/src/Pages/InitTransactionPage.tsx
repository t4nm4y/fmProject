
import { Button, FlexBox, InputField } from 'fm-library';
import { Header } from '../Components/Header';
import { useState } from 'react';
import toast from 'react-hot-toast';
import { useNavigate } from 'react-router-dom';


export function InitTransaction() {
  const navigate = useNavigate(); // Initialize useHistory hook

  const [mobileNo, setMobileNo] = useState<string | null>(null);
  const [amount, setAmount] = useState<string | null>(null);

  const backendURL = process.env.REACT_APP_BACKEND_URL;

  const handleInitiateTransaction = async () => {
    if(!mobileNo || !amount) return;
      try {
        const response = await fetch(`${backendURL}/transaction/create-session`, {
          method: 'POST',
          credentials: 'include',
				headers: {
					'Content-Type': 'application/json',
				  },
				  body: JSON.stringify({
					mobile: mobileNo,
          amount: amount
				  }),
        });

        if (response.ok) {
          const result = await response.text();
          toast(result);
          console.log("res: ", response)
          navigate(`/transaction`);
        } else {
          const result = await response.text();
          // toast.error(result);
          console.log("Err:", result);
        }
      } catch (error) {
        console.error('Error:', error);
      }
  };

  return (
    <>
      <Header />
      <FlexBox direction="column" flexStyle="center" gap="1.1em">
        <h1 className="heading">Initiate Transaction</h1>
        <InputField
          inputVariant={'base'}
          placeholder={'Enter Mobile No.'}
          label={'Mobile'}
          value={mobileNo}
          onChange={(e) => setMobileNo(e.target.value)}
        />
        <InputField inputVariant='base' placeholder={"Enter Amount"} label="Amount"
          value={amount}
          onChange={(e) => setAmount(e.target.value)}
        />
        <Button onClick={handleInitiateTransaction}>Confirm</Button>
      </FlexBox>
    </>
  );
}
