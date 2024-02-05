import React, { FC, MouseEventHandler } from 'react';
import { CommonTypes } from '../../constants';
import { ColorTokenKeys } from '../../tokens/tokens';
type Variant = 'text' | 'contained' | 'outlined';
type Size = 'compact' | 'default';
export interface ButtonProps {
    id?: string;
    children?: React.ReactNode;
    onClick?: MouseEventHandler;
    variant?: Variant;
    disabled?: boolean;
    fullWidth?: boolean;
    maxWidth?: number;
    fontSize?: number;
    lineHeight?: number;
    fontWeight?: number;
    size?: Size;
    color?: ColorTokenKeys;
    hoverColor?: ColorTokenKeys;
    loading?: boolean;
    loaderSize?: number;
    startIcon?: React.ReactNode;
    endIcon?: React.ReactNode;
}
declare const Button: FC<ButtonProps & CommonTypes>;
export { Button };
