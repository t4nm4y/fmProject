import { Button, FlexBox, InputField } from 'fm-library';
import { useNavigate } from 'react-router-dom'; // Import useLocation
import { useState, useEffect } from 'react';
import toast from 'react-hot-toast';
import Lenders from '../Components/Lenders';
import LenderDetails from '../Components/LenderDetails';
import CancelButton from '../Components/CancelButton';

interface LenderDetail {
  lender_id: number;
  lender_name: string;
  icon_link: string;
  tenure: number;
  interest_rate: number;
  emi: number;
  total_amount: number;
}

interface TenureData {
  [tenure: number]: LenderDetail[];
}

interface LendersData {
  amount: number;
  mobile: string;
  tenures: TenureData;
}


export function TransactionPage() {
  const [mobileNo, setMobileNo] = useState<string | null>(null);
  const [amount, setAmount] = useState<string | null>(null);
  const [lendersData, setLendersData] = useState<LendersData | null>(null);
  const [isLenderClicked, setLenderClicked] = useState<boolean>(false);
  const [lenderTenures, setLenderTenures] = useState<LenderDetail[] | null>(null);
  const navigate = useNavigate();

  const backendURL = process.env.REACT_APP_BACKEND_URL;
  const handleTransaction = async () => {
    try {
      const response = await fetch(`${backendURL}/transaction/get-lenders`, {
        method: 'GET',
        credentials: 'include',
      });

      if (response.ok) {
        const result = await response.json();
        if(result.error){
          navigate('/');
        }
        setLendersData(result);
        setMobileNo(result.mobile);
        setAmount(result.amount);
        console.log("res: ", result); // Log result.json directly
      } else {
        console.log("Err: ", response);
      }
    } catch (error) {
      console.error('Error:', error);
    }
  };

  const handleCancelTransaction = async () => {
    try {
      const response = await fetch(`${backendURL}/transaction/cancel`, {
        method: 'GET',
        credentials: 'include',
      });

      console.log("cancel response: ", response);
      navigate('/');
    } catch (error) {
      console.error('Error:', error);
    }
  };

  const handleLenderClick = (tenure: LenderDetail[]) => {
    setLenderClicked(true);
    setLenderTenures(tenure);
    console.log("clicked: ", tenure);
  };

  const handleTenureClick = (tenure: number) => {
    setLenderClicked(true);
    console.log("clicked!");
  };

  const handleBack = () => {
    setLenderClicked(false);
  };

  useEffect(() => {
    handleTransaction();
  }, []);



  return (
    <>
      <FlexBox direction="column" flexStyle="center" gap="1.1em">
        <h1 className="heading">Transaction Page</h1>
        <FlexBox gap='5em'>
          <FlexBox>
            Mobile: &nbsp;<p className="subHeading">{mobileNo}</p>
          </FlexBox>
          <FlexBox>
            Amount: &nbsp;<p className="subHeading"> &#8377;{amount}</p>
          </FlexBox>
        </FlexBox>
        <br />
        {lendersData !== null && !isLenderClicked ? (
          <Lenders lenders={lendersData} onLenderClick={handleLenderClick} />
        ) : (
          ""
        )}
        {isLenderClicked && lenderTenures ? (
          <LenderDetails tenures={lenderTenures} onTenureClick={handleTenureClick} handleBack={handleBack} />
        ) : (
          ""
        )}
        <CancelButton/>
        <br />
      </FlexBox>
    </>
  );
}
