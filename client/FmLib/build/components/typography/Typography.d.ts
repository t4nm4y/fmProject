import { TypographyVariant } from '@mui/material';
import { FC, MouseEventHandler } from 'react';
import { ColorTokenKeys } from '../../tokens/tokens';
import { CommonTypes } from '../../constants';
export type TypographyProps = {
    id?: 'string';
    color?: ColorTokenKeys;
    variant?: TypographyVariant;
    children: any;
    align?: 'center' | 'inherit' | 'justify' | 'left' | 'right';
    onClick?: MouseEventHandler;
} & CommonTypes;
declare const Typography: FC<TypographyProps>;
export { Typography };
