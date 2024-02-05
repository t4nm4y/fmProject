import { TextFieldProps } from '@mui/material';
import { DatePickerProps } from '@mui/x-date-pickers';
import { CommonTypes, GenericExludedTypesFromMui } from '../../constants';
import { Dayjs } from 'dayjs';
export type InputVariants = 'mobile' | 'amount' | 'fullpan' | 'name' | 'pincode' | 'date' | 'base' | 'split';
type CustomOnChange = {
    onChange?: (e: any, enteredValue?: any) => void;
};
export type InputProps = Omit<TextFieldProps, GenericExludedTypesFromMui | 'margin' | 'onChange'> & CommonTypes & CustomOnChange & {
    clearValue?: boolean;
};
export type InputPropsWithVariant = {
    inputVariant: InputVariants;
} & InputProps;
export type AmountInputProps = {
    inputVariant: 'amount';
    amountInputProps?: {
        maxAmountLength?: number;
    };
} & InputProps;
export type FmDatePickerProps = {
    inputVariant: 'date';
    id?: string;
    label: string;
    value?: Dayjs;
    defaultValue?: Dayjs;
    onChange?: (value: Dayjs) => void;
    fullWidth?: boolean;
    maxWidth?: number;
} & Omit<DatePickerProps<Dayjs>, GenericExludedTypesFromMui | 'onChange' | 'margin'> & CommonTypes;
export type BaseSplitInputProps = {
    className?: string;
    containerStyle?: object;
    disabledStyle?: object;
    errorStyle?: object;
    focusStyle?: object;
    hasErrored?: boolean;
    inputStyle?: object;
    isDisabled?: boolean;
    isInputNum?: boolean;
    isInputSecure?: boolean;
    noOfInputs: number;
    onChange: (value: string) => any;
    placeholder?: string;
    separator?: object;
    shouldAutoFocus?: boolean;
    value: string | number;
    clearValue?: boolean;
};
export type OtpInputProps = {
    inputVariant: 'split';
} & BaseSplitInputProps;
export {};
