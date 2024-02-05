import { FC } from 'react';
import { AmountInputProps, FmDatePickerProps, InputPropsWithVariant, OtpInputProps } from './types';
type InputFieldProps = AmountInputProps | OtpInputProps | InputPropsWithVariant | FmDatePickerProps;
export declare const InputField: FC<InputFieldProps>;
export {};
