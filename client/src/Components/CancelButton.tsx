import { Button } from 'fm-library'
import { useNavigate } from 'react-router-dom';

const CancelButton = () => {
    const backendURL = process.env.REACT_APP_BACKEND_URL;
    const navigate = useNavigate();
    const handleCancelTransaction = async () => {
        try {
          // Display a confirmation dialog
          const isConfirmed = window.confirm("Are you sure you want to cancel the transaction?");
      
          if (isConfirmed) {
            const response = await fetch(`${backendURL}/transaction/cancel`, {
              method: 'GET',
              credentials: 'include',
            });
      
            console.log("cancel response: ", response);
            navigate('/');
          } else {
            // console.log("Transaction cancellation canceled by user.");
          }
        } catch (error) {
          console.error('Error:', error);
        }
      };
      
    return (
        <Button color='color.error.shade2' size='compact' onClick={handleCancelTransaction}>
            Cancel
        </Button>
    )
}

export default CancelButton