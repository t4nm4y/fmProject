import React, { FC, MouseEventHandler } from 'react';
import { ColorTokenKeys } from '../../types';
interface ModalProps {
    open: boolean;
    closeModal: MouseEventHandler;
    title?: string;
    subTitle?: string;
    btnText?: string;
    secBtnText?: string;
    handleBtnClick?: MouseEventHandler;
    secHandleBtnClick?: MouseEventHandler;
    icon?: string | React.ReactNode;
    btnColor?: ColorTokenKeys;
    secBtnColor?: ColorTokenKeys;
    showBar?: boolean;
    barColor?: ColorTokenKeys;
    showCrossIcon?: boolean;
    closeOnBackdrop?: boolean;
    customContent?: boolean;
    children?: React.ReactNode | React.ReactNode[];
    maxWidth?: number;
}
export declare const Modal: FC<ModalProps>;
export {};
