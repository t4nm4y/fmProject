import { FC } from 'react';
import { SnackbarProps } from '@mui/material';
type AdditionalSnackbarProps = {
    title: string;
    subtitle: string;
    type: 'success' | 'error' | 'info';
};
type CustomSnackbarProps = Omit<SnackbarProps, 'style' | 'classes'> & AdditionalSnackbarProps;
declare const Snackbar: FC<CustomSnackbarProps>;
export { Snackbar };
